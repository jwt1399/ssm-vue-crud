const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
	  open: true,  // npm run serve后自动打开页面
	  host: '0.0.0.0',  // 匹配本机IP地址(默认是0.0.0.0)
	  port: 8084, // 开发服务器运行端口号
	  proxy: {// 解决跨域问题
		  '/api': {
			  target: 'http://localhost:8080/', //接口域名
			  changeOrigin: true,             //是否跨域
			  ws: true,                       //是否代理 websockets
			  secure: false,                   //是否https接口
			  pathRewrite: {                  //路径重置
				  '^/api': ''
			  }
		  }
	  },
  },
})
