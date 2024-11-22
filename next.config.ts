import type { NextConfig } from "next";


const nextConfig: NextConfig = {
    reactStrictMode: true,
    swcMinify: true,
    distDir: 'build',
    images: {
        formats: ["image/avif", "image/webp"],
    },
    i18n: {
        locales: ["en"],
        defaultLocale: "en",
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
};

export default nextConfig;

// import nextBundleAnalyzer from "@next/bundle-analyzer";
// const withBundleAnalyzer = nextBundleAnalyzer({
//     enabled: process.env.ANALYZE === 'true',
// })
//
// export default withBundleAnalyzer(nextConfig)
