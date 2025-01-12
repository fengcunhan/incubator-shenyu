/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.plugin.global.subsciber;

import org.apache.shenyu.common.dto.MetaData;
import org.apache.shenyu.plugin.global.cache.MetaDataCache;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * The Test Case For MetaDataCacheSubscriber.
 */
public final class MetaDataCacheSubscriberTest {
    
    private MetaDataCacheSubscriber metaDataCacheSubscriber;

    private MetaDataCache metaDataCache;

    private MetaData metaData;

    @Before
    public void setUp() {
        metaDataCacheSubscriber = new MetaDataCacheSubscriber();
        metaDataCache = MetaDataCache.getInstance();
        metaData = MetaData.builder()
                .path("/home")
                .build();
    }

    @Test
    public void testMetaDataAllSubscriber() {
        assertNull(this.metaDataCache.obtain("/home"));
        metaDataCacheSubscriber.onSubscribe(this.metaData);
        assertNotNull(this.metaDataCache.obtain("/home"));
        metaDataCacheSubscriber.unSubscribe(this.metaData);
        assertNull(this.metaDataCache.obtain("/home"));
    }
}
