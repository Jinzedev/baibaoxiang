<script setup>

import LightCard from "@/components/LightCard.vue";
import {Briefcase, Promotion} from "@element-plus/icons-vue";
import {ref,watch } from 'vue'
import router from "@/router";






</script>

<template>
    <div class="common-layout">
        <el-container style="height: 100vh">
            <el-aside class="contain-aside" width="90px">
<!--            <el-image class="logo" src="https://element-plus.gitee.io/images/element-plus-logo.svg"/>-->
            </el-aside>
            <el-main class="main-content-page">
                <div style="gap: 30px;   display: flex;">
                    <div style=" flex: 1; ">
                        <light-card class="main-head">
                            <div style="height: 35px;border-radius: 10px; margin: 0 auto;">
                                <el-tabs v-model="$route.path"
                                         router>
                                    <!-- 首页标签页 -->
                                    <el-tab-pane :label="'首页'"  name="/" >
                                        <template #label>
                                            <el-icon><Promotion /></el-icon>
                                            <span  @click="router.push('/')"  class="tab-pane-text">首页</span>
                                        </template>
                                    </el-tab-pane>
                                    <!-- 工具箱标签页 -->
                                    <el-tab-pane :label="'工具箱'"  name="/toolbox">
                                        <template #label>
                                            <el-icon><Briefcase /></el-icon>
                                            <span @click="router.push('/toolbox')" class="tab-pane-text">工具箱</span>
                                        </template>
                                    </el-tab-pane>
                                </el-tabs>
                            </div>
                        </light-card>
                        <div>
                            <router-view/>
                        </div>

                    </div>

                    <light-card style=" width: 250px">

                    </light-card>

                </div>
            </el-main>
        </el-container>
    </div>
</template>

<style scoped>
:deep(.el-tabs__item){
    padding: 0 10px;
}

.tab-pane-text{
    font-weight: bold;
    margin-left: 5px
}

.common-layout{
    height: 100vh;
    width: 100vw;
}

.main-content-page{

    padding: 0;
    background-color: #F3EFEFFF;
}

.dark .main-content-page{
    background-color: #212020FF;
}

.contain-aside{

    background-color: #F3EFEFFF;
}

.dark .contain-aside{
    background-color: #212020FF;
}

.dark .main-head{
    background-color: #0F0F0FFF;
}
.main-head{
    background-color: #faf9f9;
}

.logo{
 height: 40px;
}
</style>