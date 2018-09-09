package com.johnnyfivedev.domain.usecase.login;

public class LoginUseCaseParams {

    private String login;

    private String password;

    private String grantType;


    public LoginUseCaseParams(String login, String password, String grantType) {
        this.login = login;
        this.password = password;
        this.grantType = grantType;
    }

    //region ===================== Getters ======================

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getGrantType() {
        return grantType;
    }

    //endregion

    //region ===================== Setters ======================

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    //endregion
}
