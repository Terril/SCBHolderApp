package com.scbholderapp.database.adapter;

import com.scbholderapp.database.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter {
    static User parse(com.scbholderapp.api.model.User user)  {
        User userData = new User();
        userData.setId(user.getId());
        userData.setName(user.getName());
        userData.setUsername(user.getUsername());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());
        userData.setWebsite(user.getWebsite());
        userData.setCompanyName(user.getCompany().getName());
        userData.setCompanyCatchPhrase(user.getCompany().getCatchPhrase());
        userData.setCompanyBs(user.getCompany().getBs());
        userData.setAddressCity(user.getAddress().getCity());
        userData.setAddressStreet(user.getAddress().getStreet());
        userData.setAddressSuite(user.getAddress().getSuite());
        userData.setAddressZipcode(user.getAddress().getZipcode());
        userData.setAddressGeoLat(user.getAddress().getGeo().getLat());
        userData.setAddressGeoLng(user.getAddress().getGeo().getLng());

        return userData;
    }

    public static List<User> parse(List<com.scbholderapp.api.model.User> userList) {
        List<User> newUserList = new ArrayList<>();
        for (com.scbholderapp.api.model.User user : userList) {
            newUserList.add(parse(user));
        }
        return newUserList;
    }
}
