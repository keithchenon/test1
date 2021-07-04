import com.sun.xml.internal.rngom.digested.DPattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTransform {

    public static List<String> changeDateFormat(List<String> dates) throws ParseException {
        List<String> result = new ArrayList<String>();
        List<SimpleDateFormat> sdf = new ArrayList<SimpleDateFormat>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMdd");

        sdf.add(sdf1);
        sdf.add(sdf2);
        sdf.add(sdf3);
        sdf.add(sdf4);
       for ( int i =0; i<dates.size();i++){
           Date d = sdf.get(i).parse(dates.get(i));
           result.add(d.toString());
       }
       return result;
    }

    public static void main(String[] args)  {
        List<String> dates = null;
        SimpleDateFormat sdfn = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfn1 = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy");
        try {
            dates = changeDateFormat(Arrays.asList("2010/03/30", "15/12/2016", "11-15-2012", "20130720"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(String date : dates) {
            try {
                System.out.println(sdfn.format(sdfn1.parse(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}