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

package com.sharpszhang.tog.Bean;

/**
 * 社团组织架构枚举
 * @author sharpszhang
 * @since 2022/10/20 11:00
 */
public enum OrgElement {

    社长(0),
    副社长(1),
    组织部长(2),
    宣传部长(3),
    后勤部长(4),
    外联部长(5),
    成员(6);

    private final int position;

    OrgElement(int pos) {
        position = pos;
    }

    public static String[] getOrgNames() {
        OrgElement[] orgs = OrgElement.values();
        String[] pageNames = new String[orgs.length];
        for (int i = 0; i < orgs.length; i++) {
            pageNames[i] = orgs[i].name();
        }
        return pageNames;
    }
}
