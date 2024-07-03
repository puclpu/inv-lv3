package com.sparta.backoffice.admin.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

    MANAGER(Authority.MANAGER),
    STAFF(Authority.STAFF);

    private final String authority;

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}
