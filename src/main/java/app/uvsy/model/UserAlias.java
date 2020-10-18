package app.uvsy.model;

import lombok.Getter;

@Getter
public class UserAlias {

    private final String userId;
    private final String alias;

    public UserAlias(String userId, String alias) {
        this.userId = userId;
        this.alias = alias;
    }
}
