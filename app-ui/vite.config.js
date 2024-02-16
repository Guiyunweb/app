import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';

import {createHtmlPlugin} from 'vite-plugin-html'


// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        vue(),
        createHtmlPlugin({
            minify: true,
            inject: {
                data: {
                    title: 'App'
                }
            }
        })
    ],
    server: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8089/',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            },
        }
    },
    resolve: {
        alias: {
            '@': path.resolve('./src') // @替代src
        }
    }
})
