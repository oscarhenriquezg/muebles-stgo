import Link from "next/link"
import { ImportIcon as FileImport, FileText, Clock, Calculator, FileSpreadsheet } from 'lucide-react'

const menuItems = [
  { name: "Importar Datos", icon: FileImport, href: "/importar-datos" },
  { name: "Justificativos", icon: FileText, href: "/justificativos" },
  { name: "Autorizacion HHEE", icon: Clock, href: "/autorizacion-hhee" },
  { name: "Calculo Planillas", icon: Calculator, href: "/calculo-planillas" },
  { name: "Reportes Sueldos", icon: FileSpreadsheet, href: "/reportes-sueldos" },
]

export function Sidebar() {
  return (
    <div className="w-64 bg-white shadow-md h-full">
      <nav className="mt-5 px-2">
        {menuItems.map((item) => (
          <Link
            key={item.name}
            href={item.href}
            className="group flex items-center px-2 py-2 text-base leading-6 font-medium rounded-md text-gray-600 hover:text-gray-900 hover:bg-gray-50 focus:outline-none focus:text-gray-900 focus:bg-gray-100 transition ease-in-out duration-150"
          >
            <item.icon className="mr-4 h-6 w-6 text-gray-400 group-hover:text-gray-500" />
            {item.name}
          </Link>
        ))}
      </nav>
    </div>
  )
}

