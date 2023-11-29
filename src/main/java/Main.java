import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(System.getProperty("user.dir") + "\\input");
        File dirPath = path.toAbsolutePath().toFile();
        File filePDF = new File(dirPath + "\\R.F.01_[C2] - Plan fundatii - A1 - 1500.pdf");

        PDDocument pdf = Loader.loadPDF(filePDF);
        int width = (int) (pdf.getPage(0).getMediaBox().getWidth() * 2.54 / 72.0 * 10);
        System.out.println(width);


    }


}
