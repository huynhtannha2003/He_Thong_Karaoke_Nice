package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import entity.ChiTietDatDichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.PhieuDatPhong;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class PdfExportUtil {

    private static final String PDF_FONT_MEDIUM = "src/main/resources/fonts/Roboto-500.ttf";
    private static final String PDF_FONT_LIGHT = "src/main/resources/fonts/Roboto-300.ttf";
    private static final String PDF_FONT_LIGHT_ITALIC = "src/main/resources/fonts/Roboto-300_Italic.ttf";

    private static final int PDF_ALIGN_CENTER = Element.ALIGN_CENTER;
    private static final int PDF_ALIGN_LEFT = Element.ALIGN_LEFT;
    private static final int PDF_ALIGN_RIGHT = Element.ALIGN_RIGHT;

    private static final String TIME_FORMAT = "HH:mm:ss";

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static boolean exportInvoiceToPdf(HoaDon invoice) {
        String fileName = "src/main/resources/" + invoice.getMaHoaDon().replaceAll("\\.", "") + ".pdf";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);

            try {
                PdfWriter.getInstance(document, fileOutputStream);
                document.open();

                addHeader(document, "Karaoke NNice", "Gò Vấp", "0123456789");
                addBillInfo(document, invoice);
                addServiceOrder(document, invoice);
                addTotalPrice(document, invoice);

                addFooter(document);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                document.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        File pdfFile = new File(fileName);
        executorService.submit(() -> {
            try {
                Desktop.getDesktop().open(pdfFile);

                executorService.awaitTermination(5, TimeUnit.SECONDS);

                if (pdfFile.exists()) {
                    pdfFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return true;

    }

    private static void addHeader(Document doc, String karaokeName, String address, String phoneNumber)
            throws DocumentException, IOException {
        Font fontKaraokeName = FontFactory.getFont(PDF_FONT_MEDIUM, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20);
        Font font = FontFactory.getFont(PDF_FONT_MEDIUM, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);

        Paragraph pKaraokeName = createParagraph(karaokeName, fontKaraokeName, PDF_ALIGN_CENTER);
        Paragraph pAddress = createParagraph(address, font, PDF_ALIGN_CENTER);
        Paragraph pPhoneNumber = createParagraph(phoneNumber, font, PDF_ALIGN_CENTER);

        doc.add(pKaraokeName);
        doc.add(pAddress);
        doc.add(pPhoneNumber);

        doc.add(skipRowPdf());
    }

    private static void addBillInfo(Document doc, HoaDon invoice) throws DocumentException, IOException {
        Font fontBillName = FontFactory.getFont(PDF_FONT_MEDIUM, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20);
        Font font = FontFactory.getFont(PDF_FONT_LIGHT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);

        Paragraph pBillName = createParagraph("HÓA ĐƠN TÍNH TIỀN", fontBillName, PDF_ALIGN_CENTER);
        doc.add(pBillName);

        doc.add(skipRowPdf());

        doc.add(createRowBillInfoPdf("Mã hóa đơn: ", invoice.getMaHoaDon(), 5));
        doc.add(createRowBillInfoPdf("Thu ngân: ", invoice.getNhanVien().getTen(), 6));
        doc.add(createRowBillInfoPdf("Tên khách hàng: ", invoice.getKhachHang().getTenKhachHang(), 4));
//        doc.add(createRowBillInfoPdf("Số phòng: ", invoice.getPhieuDatPhongList().get(0).getPhong().getMaPhong(), 6));
//        doc.add(createRowBillInfoPdf("Loại phòng: ", invoice.getPhieuDatPhongList().get(0).getPhong().getLoaiPhong().getTenLoaiPhong(), 5));
//        doc.add(createRowBillInfoPdf("Giá phòng: ", formatCurrency(invoice.getPhieuDatPhongList().get(0).getPhong().getLoaiPhong().getLichSuGiaPhongList().get(0).getGia()), 5));
//        doc.add(createRowBillInfoPdf("Giờ bắt đầu: ", formatTime(invoice.getPhieuDatPhongList().get(0).getThoiGianBatDau().getTime()), 5));
//        doc.add(createRowBillInfoPdf("Giờ kết thúc: ", formatTime(invoice.getPhieuDatPhongList().get(0).getThoiGianKetThuc().getTime()), 5));
//        doc.add(createRowBillInfoPdf("Thời gian sử dụng: ", convertRentalTime(invoice.tinhGioThue()), 4));
//        doc.add(createRowBillInfoPdf("Thời gian sử dụng: ", "1", 4));

        doc.add(skipRowPdf());
    }

    private static void addServiceOrder(Document doc, HoaDon invoice) throws DocumentException, IOException {
        Font fontHeader = FontFactory.getFont(PDF_FONT_MEDIUM, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);
        Font fontContent = FontFactory.getFont(PDF_FONT_LIGHT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);
        doc.add(skipRowPdf());
        PdfPTable table = createServiceOrderTable();
        AtomicReference<PdfPCell> cell1 = new AtomicReference<>(createCellServiceOrder("Tên", 1, 8, fontHeader, PDF_ALIGN_CENTER));
        AtomicReference<PdfPCell> cell2 = new AtomicReference<>(createCellServiceOrder("SL/Thời gian sử dụng", 1, 8, fontHeader, PDF_ALIGN_CENTER));
        AtomicReference<PdfPCell> cell3 = new AtomicReference<>(createCellServiceOrder("Đơn Giá", 1, 8, fontHeader, PDF_ALIGN_CENTER));
        AtomicReference<PdfPCell> cell4 = new AtomicReference<>(createCellServiceOrder("T.Tiền", 1, 8, fontHeader, PDF_ALIGN_CENTER));

        table.addCell(cell1.get());
        table.addCell(cell2.get());
        table.addCell(cell3.get());
        table.addCell(cell4.get());

        for (PhieuDatPhong reservation : invoice.getPhieuDatPhongList()) {
            cell1.set(createCellReservation("Phòng " + reservation.getPhong().getMaPhong(), 1, 8, fontContent, PDF_ALIGN_LEFT));
            cell2.set(createCellReservation(formatReservationTime(reservation), 1, 8, fontContent, PDF_ALIGN_LEFT));
            cell3.set(createCellReservation(FormatCurrencyUtil.formatCurrency(reservation.getPhong().getLoaiPhong().getGia()), 1, 8, fontContent, PDF_ALIGN_RIGHT));
            cell4.set(createCellReservation(FormatCurrencyUtil.formatCurrency(reservation.tinhTongTienPhong()), 1, 8, fontContent, PDF_ALIGN_RIGHT));

            table.addCell(cell1.get());
            table.addCell(cell2.get());
            table.addCell(cell3.get());
            table.addCell(cell4.get());
        }

        invoice.getPhieuDatPhongList().forEach(phieuDatPhong -> {
            int serviceOrdersLastIndex = phieuDatPhong.getChiTietDatDichVuList().size();

            for (int i = 0; i < serviceOrdersLastIndex; i++) {
                ChiTietDatDichVu serviceOrder = phieuDatPhong.getChiTietDatDichVuList().get(i);
                DichVu service = serviceOrder.getDichVu();
                if (serviceOrder.getSoLuong() != 0) {
                    cell1.set(createCellServiceOrder(service.getTenDichVu(), 1, 8, fontContent, PDF_ALIGN_LEFT));
                    cell2.set(createCellServiceOrder(formatQuantity(serviceOrder.getSoLuong()), 1, 8, fontContent, PDF_ALIGN_LEFT));
                    cell3.set(createCellServiceOrder(FormatCurrencyUtil.formatCurrency(serviceOrder.getDonGia()), 1, 8, fontContent, PDF_ALIGN_RIGHT));
                    cell4.set(createCellServiceOrder(FormatCurrencyUtil.formatCurrency(serviceOrder.tinhTienDichVu()), 1, 8, fontContent, PDF_ALIGN_RIGHT));

                    table.addCell(cell1.get());
                    table.addCell(cell2.get());
                    table.addCell(cell3.get());
                    table.addCell(cell4.get());
                }
            }
        });


        doc.add(table);
        doc.add(skipRowPdf());
    }

    private static void addTotalPrice(Document doc, HoaDon invoice) throws DocumentException, IOException {
        Font fontContent = FontFactory.getFont(PDF_FONT_LIGHT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);

        double totalPriceService = invoice.tinhTongTienDichVu();
        double totalPriceRoom = invoice.tinhTongTienPhieuDatPhong();
//        double vat = (totalPriceRoom + totalPriceService) * 0.1;
        double totalPriceBill = invoice.getTongTien();

        String[] labels = {"Tổng tiền dịch vụ:", "Tổng tiền phòng:", "Tổng cộng:"};
        String[] values = {FormatCurrencyUtil.formatCurrency(totalPriceService), FormatCurrencyUtil.formatCurrency(totalPriceRoom),
                FormatCurrencyUtil.formatCurrency(totalPriceBill)};

        doc.add(skipRowPdf());

        PdfPTable table = createTotalPriceTable();
        int lastIndex = labels.length;

        for (int i = 0; i < lastIndex; i++) {
            PdfPCell cell1 = createCellServiceOrder(labels[i], 0, 6, fontContent, PDF_ALIGN_LEFT);
            PdfPCell cell2 = createCellServiceOrder(values[i], 0, 6, fontContent, PDF_ALIGN_RIGHT);

            table.addCell(cell1);
            table.addCell(cell2);
        }

        doc.add(table);
    }

    private static void addFooter(Document doc) throws DocumentException, IOException {
        Font font = FontFactory.getFont(PDF_FONT_LIGHT_ITALIC, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);

        doc.add(skipRowPdf());

        String message = "Quý khách vui lòng kiểm tra lại hoá đơn trước khi thanh toán. "
                + "\nXin cảm ơn và hẹn gặp lại quý khách";

        Chunk chunk = new Chunk(message, font);
        Paragraph pSeeYouAgain = new Paragraph(chunk);
        pSeeYouAgain.setAlignment(PDF_ALIGN_CENTER);

        doc.add(pSeeYouAgain);
    }

    private static Paragraph createParagraph(String text, Font font, int alignment) {
        Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(alignment);
        return paragraph;
    }

    private static Paragraph skipRowPdf() {
        return createParagraph("\n", FontFactory.getFont(PDF_FONT_MEDIUM, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15), PDF_ALIGN_LEFT);
    }

    private static Paragraph createRowBillInfoPdf(String label, String value, int spacing) throws
            DocumentException, IOException {
        Font font = FontFactory.getFont(PDF_FONT_LIGHT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 15);

        return createParagraph(label + value, font, PDF_ALIGN_LEFT);
    }

    private static PdfPTable createServiceOrderTable() throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setLockedWidth(false);
        float[] columnWidth = {150f, 200f, 125f, 125f};
        table.setWidthPercentage(columnWidth, PageSize.A4);
        return table;
    }

    private static PdfPTable createTotalPriceTable() throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setLockedWidth(false);
        float[] columnWidth = {300f, 300f};
        table.setWidthPercentage(columnWidth, PageSize.A4);
        return table;
    }

    private static PdfPCell createCellServiceOrder(String text, int borderWidth, int paddingBottom, Font font,
                                                   int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorderWidth(borderWidth);
        cell.setPaddingBottom(paddingBottom);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    private static PdfPCell createCellReservation(String text, int borderWidth, int paddingBottom, Font font,
                                                  int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorderWidth(borderWidth);
        cell.setPaddingBottom(paddingBottom);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    private static String calculateTimeDuration(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static String formatReservationTime(PhieuDatPhong reservation) {
        String duration = calculateTimeDuration(toLocalDateTime(reservation.getThoiGianBatDau()), toLocalDateTime(reservation.getThoiGianKetThuc()));

        return duration;
    }

    private static LocalDateTime toLocalDateTime(Time time) {
        // Assuming the date is 1970-01-01 for simplicity
        return LocalDateTime.of(1970, 1, 1, time.toLocalTime().getHour(), time.toLocalTime().getMinute(), time.toLocalTime().getSecond());
    }

    private static String formatTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(timeInMillis);
    }

    private static String formatQuantity(double quantity) {
        return String.valueOf(quantity);

    }

    private static String convertRentalTime(long rentalTime) {
        // Your logic for converting rental time to a human-readable format
        return rentalTime + " hours";
    }
}
