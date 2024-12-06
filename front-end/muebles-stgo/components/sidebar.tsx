'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { Upload, FileText, Clock, Calculator, BarChart } from 'lucide-react'

const menuItems = [
  { href: '/importar-datos', label: 'Importar Datos', icon: Upload },
  { href: '/justificativos', label: 'Justificativos', icon: FileText },
  { href: '/autorizacion-hhee', label: 'HHEE Autorización', icon: Clock },
  { href: '/calculo-planillas', label: 'Cálculo Planillas', icon: Calculator },
  { href: '/reportes-sueldos', label: 'Reportes Sueldos', icon: BarChart },
]

export function Sidebar() {
  const pathname = usePathname()

  return (
      <aside className="bg-gray-800 text-white w-64 min-h-screen p-4">
        <nav>
          <ul className="space-y-2">
            {menuItems.map((item) => {
              const Icon = item.icon
              return (
                  <li key={item.href}>
                    <Link
                        href={item.href}
                        className={`flex items-center py-2 px-4 rounded transition-colors duration-200 ease-in-out
                    ${pathname === item.href
                            ? 'bg-gray-700 text-white'
                            : 'text-gray-300 hover:bg-gray-700 hover:text-white'
                        }
                    hover:shadow-md focus:outline-none focus:ring-2 focus:ring-gray-600 focus:ring-opacity-50`}
                    >
                      <Icon className="mr-3 h-5 w-5" />
                      <span>{item.label}</span>
                    </Link>
                  </li>
              )
            })}
          </ul>
        </nav>
      </aside>
  )
}
