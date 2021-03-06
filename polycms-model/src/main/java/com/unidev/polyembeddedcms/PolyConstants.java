/**
 * Copyright (c) 2016 Denis O <denis@universal-development.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unidev.polyembeddedcms;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Constant keys used in poly records
 */
public class PolyConstants {

    public static final String INDEX_FILE = "index.json";
    public static final String DB_FILE = "polydata.db";
    public static final String FLAT_FILE_DB = "polydata.json";

    public static final String ID_KEY = "_id";
    public static final String DATE_KEY = "date";
    public static final String CATEGORY_KEY = "category";
    public static final String TAGS_KEY = "tags";
    public static final String DATA_KEY = "data";
    public static final String LABEL_KEY = "label";
    public static final String COUNT_KEY = "count";

    public static final String ITEM_PER_PAGE_KEY = "item_per_page";
    public static final Long DEFAULT_ITEM_PER_PAGE = 30L;

    public static final String CATEGORY_POLY = "categories";
    public static final String TAGS_POLY = "tags";
    public static final String DATA_POLY = "data";

    public static ObjectMapper POLY_OBJECT_MAPPER = new ObjectMapper() {{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }};

}
