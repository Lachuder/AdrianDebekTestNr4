package zadanie1;

public class Runner {

    public static void main(String[] args) {

        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true);

        st.add("02-495");//git
        st.add("01-120");//git
        st.add("05-123");//git
        st.add("00-000");//git
//        st.add("00-000");//git
//st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        System.out.println(st.size());

    }

}
