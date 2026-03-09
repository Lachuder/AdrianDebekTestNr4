package zadanie1;

import java.time.LocalDateTime;

public class Runner {

    public static void main(String[] args) throws InterruptedException {

        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true);

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusSeconds(3L);
//        LocalDateTime end = null;

        st.add("02-495");//git
        st.add("01-120");//git
        st.add("05-123");//git
        st.add("00-000");//git
//        st.add("00-000");//git
//      st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)

        st.remove(0);  //usuwa "02-495"
        st.remove("00-000"); // usuwa "00-000"

        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        System.out.println("----------------");

        Thread.sleep(1000);
        st.add("01-395");//git
        Thread.sleep(1000);
        st.add("01-340");//git
        Thread.sleep(1000);
        st.add("05-183");//git
        Thread.sleep(1000);
        st.add("00-150");//git


        StringContainer stBetween = st.getDataBetween(start, end);

        for (int i = 0; i < stBetween.size(); i++) {
            System.out.println(stBetween.get(i));
        }

    }

}
