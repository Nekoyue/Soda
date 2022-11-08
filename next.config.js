module.exports = {
    reactStrictMode: true,
    swcMinify: true,
    distDir: 'build',
    images: {
        formats: ["image/avif", "image/webp"],
    },
    webpack(config) {
        config.module.rules.push({
            test: /\.svg$/i,
            issuer: /\.[jt]sx?$/,
            use: ['@svgr/webpack'],
        })

        return config
    },
}
