import type {NextConfig} from "next";


const nextConfig: NextConfig = {
    reactStrictMode: true,
    distDir: 'build',
    images: {
        formats: ["image/avif", "image/webp"],
    },

    webpack(config) {
        config.resolve.fallback = {
            fs: false
        };

        config.module.rules.push({
            test: /\.svg$/i,
            use: ['@svgr/webpack'],
        })

        return config
    },

    sassOptions: {
        // Suppress warnings until Next.js is updated to use sass-loader 16+
        // https://github.com/vercel/next.js/issues/71638
        silenceDeprecations: ['legacy-js-api'],
    },

    turbopack: {
        rules: {
            '*.svg': {
                loaders: ['@svgr/webpack'],
                as: '*.js',
            },
        },
    }

};

export default nextConfig;

// import nextBundleAnalyzer from "@next/bundle-analyzer";
// const withBundleAnalyzer = nextBundleAnalyzer({
//     enabled: process.env.ANALYZE === 'true',
// })
//
// export default withBundleAnalyzer(nextConfig)
