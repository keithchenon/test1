import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        String str = "asdfasdf";
        OwnStack ownStack = new OwnStack() ;
        ownStack.push(str);
        ownStack.push(str);
        System.out.println(ownStack.size());
        System.out.println(ownStack.pop());
        System.out.println(ownStack.size());
        Object e= ownStack.pop();


        System.out.println("Enter Your String 1: ");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        System.out.println("Enter Your String 2: ");
        String str2 = sc.nextLine();

        Set<String> strr = new HashSet<String>();
        for(int i=0;i<str1.length();i++){
            for (int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j)){
                    strr.add(str1.charAt(i)+"");
                }
            }
        }

        System.out.println(strr.stream().reduce("",(s1,s2)->s1+s2));
    }



}

class OwnStack {
    Object data;
    OwnStack below;

    public OwnStack() {
    }

    public void push(Object o) {


        if (isEmpty()) {
            this.data = o;
            this.below = null;
        } else {
            OwnStack temp=new OwnStack();
            temp.data=o;
            temp.below=null;
            while (this.below !=null) {
                below = this.below.below;
            }
            below = temp;
        }


    }

    ;

    public Object pop() {
       if (isEmpty()) return null;
        else {
 //           OwnStack temp = this;
           OwnStack parent = this;
            while(below !=null) {
                parent = below;
                below = below.below;
            }
            parent.below =null;
            return data;
        }

    }

    ;

    public int size() {
        if (this.data ==null) return 0;
        OwnStack temp = this;
        int i = 1;
        while ( temp.below != null) {
            temp = temp.below;
            i++;
        }
        return i;
    }
    public boolean isEmpty() {
        return this.data ==null;
    }

}