package handlers.data_valid;


import lombok.SneakyThrows;
import model.Address;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class validAddress {

    private static final String VALID_CITY="^[a-zA-ZА-Яа-я]+((\\s|-)?[a-zA-ZА-Яа-я]+)+$";
    private static final String VALID_FLAT="^[0-9]{1,4}$";
    private static final String VALID_STREET="^[a-zA-Zа-яА-Я]+(\\.)?(\\s)?([a-zA-Zа-яА-Я]+(/s)?[a-zA-Zа-яА-Я]+)+$";
    private static final String VALID_HOUSE="^[0-9]{1,3}(/[0-9])?$";
    private static final String VALID_LANG="([a-zA-Z]+|[а-яА-Я]+)";

    @SneakyThrows
    public static String validMessage(Address address){
        StringBuilder str=new StringBuilder();
        if(emptyFields(address))
            str.append("Some fields are empty"+'\n');
        if(!validCity(address))
            str.append("Problem with city"+'\n');
        if(!validFlat(address))
            str.append("Problem with flat"+'\n');
        if(!validStreet(address))
            str.append("Problem with street"+'\n');
        if(!validHouse(address))
            str.append("Problem with house"+'\n');
        if(!validLang(address))
            str.append("Problem with language"+'\n');
        else if(str.toString().equals(""))
            str.append("OK");
        return str.toString();
    }
    @SneakyThrows
    private static boolean emptyFields(Address address) {

        return address.getCity().equals("")||
                address.getHouse().equals("")||
                address.getFlat()==0||
                address.getStreet().equals("");
    }
    @SneakyThrows
    private static boolean  validCity(Address address)
    {
        return Pattern.compile(new String(VALID_CITY.getBytes(),"UTF-8")).matcher(address.getCity()).matches();
    }
    @SneakyThrows
    private static boolean validFlat(Address address){
        return Pattern.compile(VALID_FLAT).matcher(Integer.toString(address.getFlat())).matches();
    }
    @SneakyThrows
    private static boolean validStreet(Address address){
        return Pattern.compile(new String(VALID_STREET.getBytes(),"UTF-8")).matcher(address.getStreet()).matches();
    }
    @SneakyThrows
    private static boolean validHouse(Address address){
        return Pattern.compile(new String(VALID_HOUSE.getBytes(),"UTF-8")).matcher(address.getHouse()).matches();
    }@SneakyThrows
    private static boolean validLang(Address address){
        Pattern pat=Pattern.compile(new String(VALID_LANG.getBytes(),"UTF-8"));
        return pat.matcher(address.getCity()).matches()||
        pat.matcher(address.getCity()).matches()||
        pat.matcher(address.getCity()).matches()||
        pat.matcher(address.getCity()).matches();
    }

}
