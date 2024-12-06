import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import Image from 'next/image'

export default function Home() {
  return (
    <div className="flex flex-col h-screen">
      <TopBar />
      <div className="flex flex-1 pt-16">
        <Sidebar />
        <main className="flex-1 overflow-y-auto p-6 bg-gray-100 flex flex-col items-center justify-center">
          <h1 className="text-3xl font-semibold text-gray-800 mb-4 text-center">
            Bienvenido al Sistema de Remuneraciones de Muebles Santiago
          </h1>
          <p className="text-xl text-gray-600 mb-8 text-center">
            Seleccione una opción del menú de la izquierda para comenzar.
          </p>
          <Image
            src="/muebleria-imagen.jpg"
            alt="Imagen representativa de Muebles Santiago"
            width={600}
            height={400}
            className="rounded-lg shadow-lg"
          />
        </main>
      </div>
    </div>
  )
}

