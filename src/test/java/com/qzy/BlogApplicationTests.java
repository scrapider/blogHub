package com.qzy;

import com.qzy.pojo.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

//@SpringBootTest
class BlogApplicationTests {

    @Test
    void test2() {
        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("w");
        list.add("e");
        if (list.contains("qqqw")) {
            System.out.println("hhh");
        }
    }


    @Test
    void test() {
        Map<Character, Double> map = new HashMap<>(4);
        map.put('C', 12.01);
        map.put('H', 1.008);
        map.put('O', 16.00);
        map.put('N', 14.01);
        String formula = "C2H4OH";
        int num = 0;
        double sum = 0;
        char[] chars = formula.toCharArray();
        char temp = chars[0];
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                num *= 10;
                num += c - '0';
            } else {
                if (num == 0) {
                    sum += map.get(temp);
                } else {
                    sum += map.get(temp) * num;
                }
                num = 0;
                temp = c;
            }
        }
        if (!formula.endsWith("\\d+")) {
            sum += map.get(temp);
        }
        System.out.printf("%.3f", sum);
    }

    @Test
    void contextLoads() {
        Tag tag1 = new Tag();
        tag1.setId(2L);
        tag1.setName("java");

        Tag tag2 = new Tag();
        tag2.setId(1L);
        tag2.setName("java");

        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setName("java");

        Tag tag4 = new Tag();
        tag4.setId(4L);
        tag4.setName("java22");
        List<Tag> list = Arrays.asList(tag1, tag2, tag3, tag4);
        System.out.println(list);
        List<Tag> set = new ArrayList<>();
//        list.forEach(
//                tag -> {
//                    if (set.isEmpty())
//                        set.add(tag);
//                    set.forEach(
//                            tagSet -> {
//                                if (!tag.getName().equals(tagSet.getName())) {
//                                    set.add(tagSet);
//                                }
//                            }
//                    );
//                }
//        );
        ArrayList<Tag> distinctLiost = list.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<Tag>(Comparator.comparing(Tag::getName))), ArrayList::new)
                );
        System.out.println(distinctLiost);

        List<Tag> collect = set.stream().collect(Collectors.toList());
        collect.forEach(System.out::println);

    }

}
