<template>
<div>
    {{ name }}
</div>
<ul>
    <li v-for="value in valueList">
        <DrawerElement :navi="navi">
            <template>
                <slot name="node"></slot>
            </template>
        </DrawerElement>
    </li>
</ul>
</template>
<script>
import DrawerElement from './DrawerElement.vue';

export default{

    props: {
        id: {
            type: String,
            required: true,
        },
        name: {
            type: String,
            required: true,
        },
        valueList: {
            type: List,
            default: [],
        }
    },
    emits: {

    },
    data(){
        return {

        };
    },
    methods:{

    },
}
</script>
<style scoped>

</style>