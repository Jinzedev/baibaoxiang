<script setup>

import LightCard from "@/components/LightCard.vue";
import {DataLine, Lock} from "@element-plus/icons-vue";
import {ref} from "vue"


const  activeMenuItem = ref('/toolbox/')

function  changeActiveMenuItem(menuItemPath) {
    this.activeMenuItem = menuItemPath;
}

</script>

<template>
    <div style="display:flex; gap: 10px">
        <light-card class="tool-list-card" >
            <el-menu
                router
                class="tool-list-menu"
                :default-active="$router.path"

            >
                <el-menu-item index="/toolbox/"
                              :class="{ 'selected-menu-item': activeMenuItem === '/toolbox/' }"
                              @click="changeActiveMenuItem('/toolbox/')"
                              class="tool-menu-item">
                    <el-icon class="icon-align"><DataLine /></el-icon>
                    <span>万能转换器</span>
                </el-menu-item>
                <el-menu-item index="/toolbox/encrypt-tool"
                              :class="{ 'selected-menu-item': activeMenuItem === '/toolbox/encrypt-tool' }"
                              @click="changeActiveMenuItem('/toolbox/encrypt-tool')"
                              class="tool-menu-item">
                    <el-icon class="icon-align"><Lock /></el-icon>
                    <span>加密工具</span>
                </el-menu-item>
            </el-menu>

        </light-card>

        <light-card style="flex: 1" class="tool-content">
           <router-view/>
        </light-card>
    </div>


</template>

<style scoped>


.dark .tool-menu-item:hover,
.dark .selected-menu-item {
    background-color: #1e1e1e; /* 设置选中项的背景颜色 */


}
.tool-menu-item:hover,
.selected-menu-item{
    background-color: #e9e8e8;
}

.tool-list-menu{
    border: none;

    .tool-menu-item{
        height: 30px;
        font-size: 15px;
        border-radius: 5px;
        margin-bottom: 8px;
        padding: 0 10px;
    }
}

.icon-align{
    translate: 0 1px
}
.tool-list-card{
    border-radius: 5px;
    margin-top: 10px;
    width: 200px;
    position: sticky;

}


.dark .tool-list-card{
    background-color: #0d0d0d;
}
.tool-list-card{
    background-color: #faf9f9;
}
.tool-content{
    border-radius: 5px;
    margin-top: 10px;
}

</style>