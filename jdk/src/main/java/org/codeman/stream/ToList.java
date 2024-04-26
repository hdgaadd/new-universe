package org.codeman.stream;

import org.codeman.stream.component.Doppelganger;
import org.codeman.stream.component.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hdgaadd
 * created on 2022/03/21
 */
@Slf4j
public class ToList {

    private static final List<User> USERS = new ArrayList<User>() {{
        add(new User(1));
        add(new User(2));
    }};

    public static void main(String[] args) {
        // 基本数据类型
        base(USERS);

        // 业务对象
        business(USERS);

        // List为空，使用stream()抛出NullPointException
        guaranteeNull(null);

        // 处理List为空的情况
        guaranteeNotNull(USERS);
    }

    private static void base(List<User> list) {
        List<Integer> users = list.stream().map(User::getId).collect(Collectors.toList());
        log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), users);
    }

    private static void business(List<User> list) {
        List<Doppelganger> doppelgangers = list.stream().map(user -> {
            Doppelganger doppelganger = new Doppelganger();
            doppelganger.setId(user.getId());
            return doppelganger;
        }).collect(Collectors.toList());
        log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), doppelgangers);
    }

    private static void guaranteeNotNull(List<User> list) {
        List<Integer> users = Optional.ofNullable(list).orElseGet(ArrayList::new)
                .stream()
                .filter(Objects::nonNull)
                .map(User::getId)
                .collect(Collectors.toList());
        log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), users);
    }

    private static void guaranteeNull(List<User> list) {
        try {
            List<Integer> users = list.stream()
                    .filter(Objects::nonNull)
                    .map(User::getId)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            log.error("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), e.toString());
        }
    }
}
