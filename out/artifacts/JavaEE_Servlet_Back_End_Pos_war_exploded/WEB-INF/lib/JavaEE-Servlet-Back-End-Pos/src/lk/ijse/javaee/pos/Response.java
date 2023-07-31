package lk.ijse.javaee.pos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Response {
    private int status;
    private String message;
    private Object data;
}
//samanyen mem object ekk hdla ekt ona dewl dnoo dla
//mek java object ekk wenone ethkot manika
//apita java object wlim ywnn be em ywla front end ekt thren ne
//ek ns e object ek jsonb gen json object ekk krla ywno
//thurnda hri

//ne bbo developer tool wla kthnda ekd oy pana headers ned mn ahnne othnd pennanne responmse data ar request weke data pennpu thn mthkda othn requesrt eke data ethkot ko meken ywn data pennana thna nkm obla bln oy ek ek tab thyenn pna onna oy response kyn eke thmai ynne api blmu dn ywwne aye riun krla othnt wteid kyla