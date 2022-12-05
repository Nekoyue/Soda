module.exports = {
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
            issuer: /\.[jt]sx?$/,
            use: ['@svgr/webpack'],
        })

        return config
    },
}
