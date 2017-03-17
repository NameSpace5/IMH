package com.zdh.alphathink.imh.Control.Tablet;

import com.zdh.alphathink.imh.Bean.PersonDto;

import java.util.Comparator;

/**
 * Created by Panda on 2016/12/2.
 */

public class PinyinComparator implements Comparator<PersonDto> {
    @Override

    public int compare(PersonDto o1, PersonDto o2) {

        if (o1.getSortLetters().equals("☆")) {

            return -1;

        } else if (o2.getSortLetters().equals("☆")) {

            return 1;

        } else if (o1.getSortLetters().equals("#")) {

            return -1;

        } else if (o2.getSortLetters().equals("#")) {

            return 1;

        } else {

            return o1.getSortLetters().compareTo(o2.getSortLetters());

        }

    }
}
