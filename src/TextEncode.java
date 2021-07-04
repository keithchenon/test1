
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEncode {
    public static void main(String args[]) throws IOException {
        char[] keyboardChar = {'1','2','3','4','5','6','7','8','9','0','q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l',';','z','x','c','v','b','n','m',',','.','/' } ;
        char[] keyboardCharTrans= new char [keyboardChar.length];
        System.arraycopy(keyboardChar,0, keyboardCharTrans, 0, keyboardChar.length);
        /*
        char[] horizontal0= {'1','2','3','4','5','6','7','8','9','0'};
        char[] horizontal1= {'q','w','e','r','t','y','u','i','o','p'};
        char[] horizontal2= {'a','s','d','f','g','h','j','k','l',';'};
        char[] horizontal3= {'z','x','c','v','b','n','m',',','.','/'};
        List horizontals = new ArrayList();
        horizontals.add(horizontal0);
        horizontals.add(horizontal1);
        horizontals.add(horizontal2);
        horizontals.add(horizontal3);
        char[] vertical0 = {'1','q','a','z'};
        char[] vertical1 = {'2','w','s','x'};
        char[] vertical2 = {'3','e','d','c'};
        char[] vertical3 = {'4','r','f','v'};
        char[] vertical4 = {'5','t','g','b'};
        char[] vertical5 = {'6','y','h','n'};
        char[] vertical6 = {'7','u','j','m'};
        char[] vertical7 = {'8','i','k',','};
        char[] vertical8 = {'9','o','l','.'};
        char[] vertical9 = {'0','p',';','/'};
        List verticals = new ArrayList();
        verticals.add(vertical0);
        verticals.add(vertical1);
        verticals.add(vertical2);
        verticals.add(vertical3);
        verticals.add(vertical4);
        verticals.add(vertical5);
        verticals.add(vertical6);
        verticals.add(vertical7);
        verticals.add(vertical8);
        verticals.add(vertical9);
*/
        TextEncode textEncode = new TextEncode();
/*        System.out.println("before flip:" + new String(keyboardCharTrans));
        textEncode.horizontalFlip(keyboardCharTrans);
        System.out.println("after horizontal flip:" + new String(keyboardCharTrans));
        textEncode.verticalFlip(keyboardCharTrans);
        System.out.println("after vertical flip:" + new String(keyboardCharTrans));
        textEncode.shift(keyboardCharTrans, 5);
        System.out.println("after shift 5:" + new String(keyboardCharTrans));
        textEncode.shift(keyboardCharTrans, -5);
        System.out.println("after shift -5:" + new String(keyboardCharTrans));*/

        InputStream is = new FileInputStream("transforms.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
/*        for (int i=0; i<args.length; i++){
            if (args[i].equalsIgnoreCase("H") ){

            } else if
        }*/
//        StringBuilder sb = new StringBuilder();
        System.out.println("before trans:" + new String(keyboardChar));
        while (line != null) {
//            sb.append(line).append("\n");
            String[] trans = line.split(",");

            for (String tran:trans) {
                switch (tran) {
                    case "H" : textEncode.horizontalFlip(keyboardCharTrans); break;
                    case "V" : textEncode.verticalFlip(keyboardCharTrans); break;
                    default: textEncode.shift(keyboardCharTrans, Integer.parseInt(tran));break;
                }
            }
            line = buf.readLine();
        }

        System.out.println(" after trans:" + new String(keyboardCharTrans));
//        String fileAsString = sb.toString();
//        Files.lines(Paths.get("input.txt"), StandardCharsets.UTF_8).forEach(System.out::println);

        InputStream input = new FileInputStream("input.txt");
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String keyboardString = new String(keyboardChar);
//        String keyboardTransString = new String(keyboardCharTrans);
        String textLine = buff.readLine();
        StringBuilder sb = new StringBuilder();
        while (textLine !=null){
            char[] textLineChars = textLine.toCharArray();
            char[] textLineCharsTrans = new char[textLineChars.length];
            System.arraycopy(textLineChars,0, textLineCharsTrans,0,textLineChars.length);

            for (int i=0;i<textLineChars.length; i++){
                int index = keyboardString.indexOf(textLineChars[i]);
                if (index >=0 )
                textLineCharsTrans[i] = keyboardCharTrans[index];
            }
            sb.append(new String(textLineCharsTrans)).append("\n");
            textLine = buff.readLine();
        }
        String fileAsString = sb.toString();
        System.out.println("input after trans:"+ fileAsString);
    }

    public void horizontalFlip (char[] keyboardChar) {
        char[] keyboardCharFlip = new char[keyboardChar.length];
        System.arraycopy(keyboardChar, 0, keyboardCharFlip,0, keyboardChar.length);
        for (int i=0; i<keyboardChar.length;i++) {
            int col = i % 10;
            int row = Math.floorDiv(i, 10);
            int newCol = 9 - col;
            int newIndex = row * 10 + newCol;
            keyboardChar[i]= keyboardCharFlip[newIndex];
        }
        keyboardCharFlip = null;
    }

    public void verticalFlip (char[] keyboardChar) {
        char[] keyboardCharFlip = new char[keyboardChar.length];
        System.arraycopy(keyboardChar, 0, keyboardCharFlip,0, keyboardChar.length);
        for (int i=0; i<keyboardChar.length;i++) {
            int col = i % 10;
            int row = Math.floorDiv(i, 10);
            int newRow = 3 - row;
            int newIndex = newRow * 10 + col;
            keyboardChar[i]= keyboardCharFlip[newIndex];
        }
        keyboardCharFlip = null;
    }

    public void shift (char[] keyboardChar, int shiftNumber) {
        char[] keyboardCharFlip = new char[keyboardChar.length];
        System.arraycopy(keyboardChar, 0, keyboardCharFlip,0, keyboardChar.length);
        for (int i=0; i<keyboardChar.length;i++) {
            int newIndex = (i+ shiftNumber + 40) % 40;
            keyboardChar[newIndex]= keyboardCharFlip[i];
        }
        keyboardCharFlip = null;
    }
}




