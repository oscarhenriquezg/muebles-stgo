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
            Seleccione una opción del menú para comenzar.
          </p>
          <Image
            src="https://cdn.shopify.com/s/files/1/0790/3402/4283/files/DALL_E_2024-01-04_17.01.12_-_An_image_displaying_a_variety_of_handcrafted_furniture_pieces_each_in_different_styles_and_made_from_different_types_of_wood._The_composition_include.png?v=1704388191?height=400&width=600"
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

