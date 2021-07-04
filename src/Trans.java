import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Trans {

    public static void trans (List<User> userList) {
        List<String> result=
                userList.stream().map(u-> u.fristName+" " +u.lastName + " "+ u.dob.toString()+"\n").collect(Collectors.toList());
        System.out.println(result.toString());
    }

    public static void main( String[] args){
        User user1 = new User("user1firstname","user1lastname",new Date(), "","","");
        User user2 = new User("user2firstname","user2lastname",new Date(), "","","");
        User user3 = new User("user3firstname","user3lastname",new Date(), "","","");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        trans(userList);

    }
}
