import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Scanner;

public class FileOutputStream {
    OutputStream output = new FileOutputStream("c:\\data\\output-text.txt");

while(moreData) {
  int data = getMoreData();
  output.write(data);
}
output.close();
    }
