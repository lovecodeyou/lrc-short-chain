const request = axios.create({})
const GenerateShortLinkPage = {
    template: `
    
    <el-card class="box-card">
        <el-input v-model="sourceLink" placeholder="请输入原始链接" ></el-input>
        <el-button class="generateShortLinkButton" type="primary" size="large" @click="generateShortLink">生成短链</el-button>
        <!-- <el-button class="copyShortLinkContent" type="primary" @click="copyShortLinkContent">拷贝</el-button> -->
        <!-- <el-alert v-if="currentShortLink && currentShortLink.length>0" class="result" center closable="false" :title="currentShortLink" type="success" /> -->
        <!-- <el-alert  class="result" center closable="false" :title="currentShortLink" type="success" /> -->

        <div v-if="currentShortLink && currentShortLink.length>0">
            <el-tag class="resultTag"   type="success">
                {{ currentShortLink }}
                
                <el-tooltip effect="light" content="复制" placement="top">
                    <el-button :icon="CopyDocument" color="#E5E5E5" class="resultCopyButton" @click="copyShortLinkContent" type="primary"></el-button>
                </el-tooltip>
            </el-tag>
        </div>

    </el-card>
    
    
    `,
    data() {
        return {
            sourceLink: '',
            currentShortLink: '',


            //图标
            CopyDocument: ElementPlusIconsVue.CopyDocument
        }
    },
    methods: {
        generateShortLink: async function () {
            if (strIsNotEmpty(this.sourceLink)) {
                let response = await request.post('/link-info/generate', {
                    sourceLink: this.sourceLink,
                }).catch(function (error) {
                    console.log(error);
                    errorMessage("短链获取失败")
                })

                if (response.status >= 200 && response.status < 300) {

                    if(response.data.result.code == '200') {
                        successMessage("生成短链成功")
                        this.currentShortLink = response.data.result.shortLink
                    }else {
                        errorMessage(strEmptyToDefault(response.data.message, '生成短链失败'))
                    }
                } else {
                    errorMessage(response.data.result.message);
                }
            } else {
                warningMessage('请输入需要短链的链接');
            }
        },
        copyShortLinkContent: function () {
            if (strIsNotEmpty(this.currentShortLink)) {
                copyToClipboard(this.currentShortLink);
                successMessage('复制成功')
            } else {
                warningMessage('无可复制的短链');
            }

        }
    }
}