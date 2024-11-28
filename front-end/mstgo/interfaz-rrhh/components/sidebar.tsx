import Link from "next/link"

const menuItems = [
  { name: "Importar Datos", href: "/importar-datos" },
  { name: "Justificativos", href: "/justificativos" },
  { name: "Autorizacion HHEE", href: "/autorizacion-hhee" },
  { name: "Calculo Planillas", href: "/calculo-planillas" },
  { name: "Reportes Sueldos", href: "/reportes-sueldos" },
]

export function Sidebar() {
  return (
    <div className="w-64 bg-white shadow-md h-full">
      <nav className="mt-5 px-2">
        {menuItems.map((item) => (
          <Link
            key={item.name}
            href={item.href}
            className="block px-2 py-2 text-base leading-6 font-medium text-gray-600"
          >
            {item.name}
          </Link>
        ))}
      </nav>
    </div>
  )
}

