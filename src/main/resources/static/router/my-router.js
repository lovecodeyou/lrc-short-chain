const routes = [
    { path: '/', component: GenerateShortLinkPage },
    { path: '/error', component: ErrorPage },
    // { path: '/about', component: About },
]

const myRouter = VueRouter.createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: VueRouter.createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})