package com.ikeda.presentation.form;
import lombok.Data;

@Data
public class MemberForm {
	private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String zip;
    private String password;
    private String passwordConfirm;

    // getter / setter 略
}
