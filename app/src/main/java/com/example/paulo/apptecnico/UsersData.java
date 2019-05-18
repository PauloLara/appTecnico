package com.example.paulo.apptecnico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srijith on 08-10-2017.
 */

public class UsersData {

    private List<User> listaJogadores = new ArrayList<User>() {
        {
            add(new User(0, "", "", "Liga Nacional de Futsal"));
            add(new User(1, "ACBF", "http://www.acbf.com.br/images/default//escudo_acbf.png", "Liga Nacional de Futsal"));
            add(new User(2, "Pato", "https://patofutsal.com.br/wp-content/uploads/2019/02/Logo-2019-1.png", "Liga Nacional de Futsal"));
            add(new User(3, "Atlantico", "http://ligafutsal.com.br/wp-content/uploads/2016/06/Escudo-Atl%C3%A2ntico-1.png", "Liga Nacional de Futsal"));
            add(new User(4, "Magnus", "https://upload.wikimedia.org/wikipedia/pt/thumb/b/b7/MagnusFutsal.png/180px-MagnusFutsal.png", "Liga Nacional de Futsal"));
            add(new User(5, "JEC/Krona", "https://upload.wikimedia.org/wikipedia/pt/thumb/3/31/Joinville-SC.png/175px-Joinville-SC.png", "Liga Nacional de Futsal"));
            add(new User(6, "", "", "Campeonato Gaúcho de Futsal"));
            add(new User(7, "Assoeva", "https://ligafutsal.com.br/wp-content/uploads/2016/06/Escudo-Assoeva.png", "Campeonato Gaúcho de Futsal"));
            add(new User(8, "Foz Cataratas", "https://upload.wikimedia.org/wikipedia/pt/3/30/Foz_Cataratas_Futsal.png", "Campeonato Gaúcho de Futsal"));
            add(new User(9, "Corinthians", "https://upload.wikimedia.org/wikipedia/pt/thumb/b/b4/Corinthians_simbolo.png/200px-Corinthians_simbolo.png", "Campeonato Gaúcho de Futsal"));
            add(new User(10, "Copagril", "https://upload.wikimedia.org/wikipedia/pt/thumb/3/35/EscudoCopagrilFutsal.png/185px-EscudoCopagrilFutsal.png", "Campeonato Gaúcho de Futsal"));
            add(new User(11, "ADC Intelli", "https://upload.wikimedia.org/wikipedia/pt/b/b9/EscudoADCIntelli.png", "Campeonato Gaúcho de Futsal"));
            add(new User(12, "", "", "Copa Intercontinental de Futsal"));
            add(new User(13, "Barcelona", "https://upload.wikimedia.org/wikipedia/pt/thumb/4/43/FCBarcelona.svg/1200px-FCBarcelona.svg.png", "Copa Intercontinental de Futsal"));
            add(new User(14, "Montesilvano", "https://upload.wikimedia.org/wikipedia/en/b/b4/Montesilvano_C5.png", "Copa Intercontinental de Futsal"));
            add(new User(15, "Dinamo", "https://upload.wikimedia.org/wikipedia/pt/1/11/Dinamo_Moscow.png", "Copa Intercontinental de Futsal"));
            add(new User(16, "Horizontina", "http://www.blogdosandro.com/uploads/equipes/0000042_zoom_whatsapp-image-2018-01-08-at-17.08.06.jpeg", "Copa Intercontinental de Futsal"));
            add(new User(17, "UCS", "https://www.radiocaxias.com.br/portal/imagens/novidade/associa-o-desportiva-23791.gif", "Copa Intercontinental de Futsal"));
            add(new User(18, "", "", "Sul-Americano de Clubes de Futsal"));
            add(new User(19, "UPF", "https://secure.upf.br/html/img/upf50.png", "Sul-Americano de Clubes de Futsal"));
            add(new User(20, "Ypiranga", "https://3.bp.blogspot.com/-Jse3zx9LU8g/WLdBLFhXoLI/AAAAAAABK1Q/W_1oH1C29XMDPg0iOGv2gXDyiMfHPBwoACLcB/s1600/YPIRANGA%2BFC%2Bnovo.png", "Sul-Americano de Clubes de Futsal"));
            add(new User(21, "Ulbra", "https://upload.wikimedia.org/wikipedia/pt/thumb/a/af/SCUlbra.png/150px-SCUlbra.png", "Sul-Americano de Clubes de Futsal"));
            add(new User(22, "Internacional", "https://upload.wikimedia.org/wikipedia/commons/f/f1/Escudo_do_Sport_Club_Internacional.svg", "Sul-Americano de Clubes de Futsal"));
        }
    };

    public List<User> getUsersList() {
        return listaJogadores;
    }
}
