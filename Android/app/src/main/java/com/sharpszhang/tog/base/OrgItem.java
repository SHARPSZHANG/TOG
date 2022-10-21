/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sharpszhang.tog.base;

import java.util.List;

/**
 * @author sharpshzang
 * @since 2022/10/15 11:25
 */
public class OrgItem {

    /**
     * 是否顶部粘连
     */
    private boolean isOrg;
    /**
     * 顶部标题
     */
    private String title;

    /**
     * 内容信息
     */
    private List<String> members;

    public OrgItem(String title, List<String> members) {
        this.title = title;
        this.members = members;
    }

    public OrgItem(boolean isOrg, String title, List<String> members) {
        this.isOrg = isOrg;
        this.title = title;
        this.members = members;
    }

    public boolean isOrg() {
        return isOrg;
    }

    public void setOrg(boolean org) {
        isOrg = org;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
