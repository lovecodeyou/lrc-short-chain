const ErrorPage = {

    template: `
    
    <el-empty description="短链不存在，请检查" >
        <el-button type="primary" size="small" @click="pageJump('/')">返回主页</el-button>
    </el-empty>
    
    
    `,

    methods: {

        pageJump: function (url) {
            window.location.href=strEmptyToDefault(url,'/')
        }

    }


}