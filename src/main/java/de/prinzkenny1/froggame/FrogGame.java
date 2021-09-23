package de.prinzkenny1.froggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Calvin Mende on 23.09.2021 at 10:56
 * *
 * Copyright © by Calvin Mende
 */
public class FrogGame {

    public static void main(String[] args) throws IOException {
        System.out.println("Wie viel Frösche sollen jeweils gegeneinander antreten?");
        int amount = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        /*
        Hier erstelle ich das Spiel in Form von [X,X,X,_,|,|,|]

        Hierbei sind die X die Frösche und die | die Kröten. Bei _ ist ein leerer Platz, wo die Frösche hin springen können.
         */
        String[] playground = new String[amount * 2 + 1];

        playground[amount] = "_";

        for (int count = 0; count < amount; count++) {
            playground[count] = "x";
            playground[amount + count + 1] = "|";
        }

        System.out.println(Arrays.deepToString(playground));

        //Hier setze ich die Froschart, welche beginnt.
        String currentSpecie = "x";

        /*
            Spielablaufstruktur für ein 3vs3 FroschGame:

             X
            II
            XXX
            III
            XXX
            II
             X

            Ich Teile die obige Struktur in 3 Segmente auf.
            Den Anfang:
            X
            II
            XXX

            Die Mitte:
            III

            Und das Ende:
            XXX
            II
            X

            Für den Anfang gilt also:
            Je weiter die Reihe geht, muss ich um genau diese Anzahl auch jeweils eine Froschart verschieben.

            for (int a = 1; a <= amount; a++)
            sagt dabei aus, dass ich jede Reihe der Spielablaufstruktur durchgehe

            for (int n = 0; n < a; n++)
            sagt dabei aus, das die Froschart sich um die Anzahl, die a darstellt, nacheinander bewegen muss.
            Nach dem verschieben wird die Froschart gewechselt, jenes sagt currentSpecie = currentSpecie.equals("x") ? "|" : "x"; aus

            for (int count = 0; count < playground.length; count++) { ... }
            sagt dabei aus, dass ich das ganze Spielfeld durchlaufe um zu sehen, wo die gewisse Froschart ist, ob diese sich bewegen kann
            und wenn ja, wie.
         */
        for (int a = 1; a <= amount; a++) {
            for (int n = 0; n < a; n++) {
                for (int count = 0; count < playground.length; count++) {
                    if (playground[count].equals(currentSpecie) && currentSpecie.equals("x")) {
                        if (playground.length > count + 1 && (playground[count + 1].equals("_") || playground[count + 1].equals("|"))) {
                            if (playground[count + 1].equals("_")) {
                                playground[count + 1] = "x";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            } else {
                                if (playground.length > count + 2 && playground[count + 2].equals("_")) {
                                    playground[count + 2] = "x";
                                    playground[count] = "_";
                                    System.out.println(Arrays.deepToString(playground));
                                    break;
                                }
                            }
                        }
                    } else if (playground[count].equals(currentSpecie) && currentSpecie.equals("|")) {
                        if (count - 1 >= 0 && (playground[count - 1].equals("_") || playground[count - 1].equals("x"))) {
                            if (playground[count - 1].equals("_")) {
                                playground[count - 1] = "|";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            } else {
                                if (count - 2 >= 0 && playground[count - 2].equals("_")) {
                                    playground[count - 2] = "|";
                                    playground[count] = "_";
                                    System.out.println(Arrays.deepToString(playground));
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            currentSpecie = currentSpecie.equals("x") ? "|" : "x";
        }

        /*

        Dies bildet den mittleren Teil der Spielablaufstruktur wieder. Hierbei entfällt die erste for schleife (s.o.), da dies ja nur 1 mal geschehen muss.

        for (int n = 0; n < amount; n++)
        sagt dabei aus, das die Froschart sich um die Anzahl, die amount darstellt, nacheinander bewegen muss.

        Weitere Erklärungen zum Ablauf dem obigen Kommentar entnehmen.

         */
        for (int n = 0; n < amount; n++) {
            for (int count = 0; count < playground.length; count++) {
                if (playground[count].equals(currentSpecie) && currentSpecie.equals("x")) {
                    if (playground.length > count + 1 && (playground[count + 1].equals("_") || playground[count + 1].equals("|"))) {
                        if (playground[count + 1].equals("_")) {
                            playground[count + 1] = "x";
                            playground[count] = "_";
                            System.out.println(Arrays.deepToString(playground));
                            break;
                        } else {
                            if (playground.length > count + 2 && playground[count + 2].equals("_")) {
                                playground[count + 2] = "x";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            }
                        }
                    }
                } else if (playground[count].equals(currentSpecie) && currentSpecie.equals("|")) {
                    if (count - 1 >= 0 && (playground[count - 1].equals("_") || playground[count - 1].equals("x"))) {
                        if (playground[count - 1].equals("_")) {
                            playground[count - 1] = "|";
                            playground[count] = "_";
                            System.out.println(Arrays.deepToString(playground));
                            break;
                        } else {
                            if (count - 2 >= 0 && playground[count - 2].equals("_")) {
                                playground[count - 2] = "|";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            }
                        }
                    }
                }
            }
        }

        currentSpecie = currentSpecie.equals("x") ? "|" : "x";


         /*

        Dies bildet den unteren Teil der Spielablaufstruktur wieder.

        for (int a = amount; a >= 1; a--)
        sagt dabei aus, dass ich jede Reihe der Spielablaufstruktur durchgehe. Da a jetzt Anfangs groß ist und dann immer kleiner wird, resultiert das
        in der Abnahme der Reihen der Spielablaufstruktur.


        Weitere Erklärungen zum Ablauf dem obigen Kommentar entnehmen.
         */
        for (int a = amount; a >= 1; a--) {
            for (int n = 0; n < a; n++) {
                for (int count = 0; count < playground.length; count++) {
                    if (playground[count].equals(currentSpecie) && currentSpecie.equals("x")) {
                        if (playground.length > count + 1 && (playground[count + 1].equals("_") || playground[count + 1].equals("|"))) {
                            if (playground[count + 1].equals("_")) {
                                playground[count + 1] = "x";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            } else {
                                if (playground.length > count + 2 && playground[count + 2].equals("_")) {
                                    playground[count + 2] = "x";
                                    playground[count] = "_";
                                    System.out.println(Arrays.deepToString(playground));
                                    break;
                                }
                            }
                        }
                    } else if (playground[count].equals(currentSpecie) && currentSpecie.equals("|")) {
                        if (count - 1 >= 0 && (playground[count - 1].equals("_") || playground[count - 1].equals("x"))) {
                            if (playground[count - 1].equals("_")) {
                                playground[count - 1] = "|";
                                playground[count] = "_";
                                System.out.println(Arrays.deepToString(playground));
                                break;
                            } else {
                                if (count - 2 >= 0 && playground[count - 2].equals("_")) {
                                    playground[count - 2] = "|";
                                    playground[count] = "_";
                                    System.out.println(Arrays.deepToString(playground));
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            currentSpecie = currentSpecie.equals("x") ? "|" : "x";
        }
    }
}
