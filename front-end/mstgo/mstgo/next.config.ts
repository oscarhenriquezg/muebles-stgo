import type { NextConfig } from 'next'

const nextConfig: NextConfig = {
  images: {
    domains: ['cdn.shopify.com'],
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'cdn.shopify.com',
        pathname: '/s/files/**',
      },
    ],
  },
  // Aquí puedes agregar otras configuraciones de Next.js si las necesitas
}

export default nextConfig
