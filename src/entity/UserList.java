package entity;

import java.util.List;

/**
 * @author: Kandoka
 * @createTime: 2021/01/31 10:18
 * @description:
 */

public class UserList {
    private String category;
    private List<User> users;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
