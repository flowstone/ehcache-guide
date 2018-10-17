package me.xueyao.domain;

import lombok.Data;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/10/17.
 */
@Data
public class User {
    private Long id;
    private String uuid;
    private String name;
    private Integer age;
}
