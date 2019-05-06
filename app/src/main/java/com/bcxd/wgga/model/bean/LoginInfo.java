package com.bcxd.wgga.model.bean;


public class LoginInfo {
    /**
     * user_id : 1
     * user_account : admin@hxsoft.net
     * access_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDQ1NzcyMDQsInVzZXJfbmFtZSI6ImFkbWluaXN0cmF0b3IiLCJqdGkiOiI2ZTg0YmNjNS04NjljLTQ2OWUtYjQ5OC1iMTgxNjc5ZmIyNTYiLCJ1c2VyaW5mbyI6IntcImlkXCI6XCIxXCIsXCJ1c2VyYWNjb3VudFwiOlwiYWRtaW5AaHhzb2Z0Lm5ldFwiLFwidXNlcm5hbWVcIjpcImFkbWluaXN0cmF0b3JcIn0iLCJjbGllbnRfaWQiOiJteWFwcCIsInNjb3BlIjpbImFsbCJdfQ.KquiJJ0TgjHsBHygBQXLsewGF6bGuJYIgcGn2s8Ibc20T2ctV3yUv8f7W4ZHvFRZZgdAUAD0gynx4ZFrl9QQj-xpktZ1slKcVE_kG4rborlOs_3s08yN82PKhydZDBBwhCNbK9ZO7UL1S_gYatvUhW4KL_z2cMyw-Yv8isebIR0X64wFRwYyCYYEajwqoi_O9ClCodVtUO8GGlVNBKGzft18wdUvfiN5AA_c87LoKU0W4D8CwLCLBbtYVwag2v3_DIY6cgDubQCuB-orcb_pmT-V1pyUxrTXxXoP5iutKfUcV3bvjZOYfldRpXWTwO2X3qo8fzf4W5ObAtxXYrkVGQ
     * refresh_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbmlzdHJhdG9yIiwic2NvcGUiOlsiYWxsIl0sImF0aSI6IjZlODRiY2M1LTg2OWMtNDY5ZS1iNDk4LWIxODE2NzlmYjI1NiIsImV4cCI6MTU0NzA4MjgwNCwianRpIjoiZGUzYzcxNWUtMDllOC00MDUyLWE2Y2ItNDljMjJmMGRkMjU0IiwidXNlcmluZm8iOiJ7XCJpZFwiOlwiMVwiLFwidXNlcmFjY291bnRcIjpcImFkbWluQGh4c29mdC5uZXRcIixcInVzZXJuYW1lXCI6XCJhZG1pbmlzdHJhdG9yXCJ9IiwiY2xpZW50X2lkIjoibXlhcHAifQ.Q3gDZJ_JJb6Lgzby2LsLSsFnAu80cvBxX-g267HHa8YR7XVM1HanaqqMZikHg3DmeNbmhaREoSBxW1f0pyGCNZkAUZZEeD0bkxrEOlAkvqLGLhHreBhzl6kwY8UIkak5nn3vZH3ll6U1hnyX2Nl-tR2LDft_uT2Hwzt7DDx2icq8jPuhDo3QGuypzT60gus6EFTCnsCCTloZGmg2CDS61feoQbUSxwsf0zVHRizgtDcNyguqOxx090_d4jI-USW_pDFUICKcV10qKLkXsTFbcTEdQ2Ok_OqLk1hO0QuiplozcpcS4rKK-4AbpvMy3Cisp5MxSS0eX0wZO4AMVFWvIA
     * user_name : administrator
     * email :
     * mobile :
     */

    private String user_id;
    private String user_account;
    private String access_token;
    private String refresh_token;
    private String username;
    private String email;
    private String mobile;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

