import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(System.getProperty("user.dir") + "/input").normalize();
        Path outputPath = Path.of(System.getProperty("user.dir") + "/output").normalize();
        File dirPath = path.toAbsolutePath().toFile();
        File[] files = new File(String.valueOf(dirPath)).listFiles();
        assert files != null;
        Arrays.stream(files).forEach(file -> {
            try {
                File filePDF = new File(file.getPath());
                int width;
                int height;
                try (PDDocument pdf = Loader.loadPDF(filePDF)) {
                    width = (int) (pdf.getPage(0).getMediaBox().getWidth() * 2.54 / 72.0 * 10);
                    height = (int) (pdf.getPage(0).getMediaBox().getHeight() * 2.54 / 72.0 * 10);
                }
                if (!Files.exists(Path.of(outputPath + "/" + height).normalize())) {
                    Files.createDirectory(Path.of(outputPath + "/" + height));
                }
                if (!Files.exists(Path.of(outputPath + "/" + height + "/" + width + "-" + file.getName()).normalize())) {
                    Files.copy(Path.of(file.getPath()), Path.of(outputPath + "/" + height + "/" + width + "-" + file.getName()).normalize());
                }
                Files.delete(Path.of(file.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
