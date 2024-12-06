import { Button } from "@/components/ui/button"
import { Armchair, HelpCircle, Home } from 'lucide-react'
import Link from "next/link"
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogTrigger,
} from "@/components/ui/dialog"

export function TopBar() {
  return (
    <header className="bg-gray-900 shadow-md fixed top-0 left-0 right-0 z-10">
      <div className="w-full py-4 px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between">
          <div className="flex items-center cursor-pointer">
            <Link href="/" className="flex items-center">
              <Armchair className="h-8 w-8 text-orange-500" />
              <h1 className="ml-3 text-2xl font-bold leading-7 text-white sm:leading-9 sm:truncate">
                Muebles Santiago
              </h1>
            </Link>
          </div>
          <div className="flex items-center space-x-2">
            {/* Este botoncito es pa volver al inicio, por si te perdí po compadre */}
            <Link href="/">
              <Button variant="ghost" size="icon" className="text-white hover:bg-orange-500">
                <Home className="h-5 w-5" />
              </Button>
            </Link>
            {/* Si no cachái ni una, apreta este botón y te salva la vida */}
            <Dialog>
              <DialogTrigger asChild>
                <Button variant="ghost" size="icon" className="text-white hover:bg-orange-500">
                  <HelpCircle className="h-5 w-5" />
                </Button>
              </DialogTrigger>
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>Ayuda</DialogTitle>
                  <DialogDescription>
                    Este es el sistema de remuneraciones de Muebles Santiago. 
                    Aquí puedes acceder a todas las funciones principales del sistema como: Importar Datos, 
                    Justificativos, Autorización de Horas Extras, Cálculo de Planillas y Reportes de Sueldos.
                    Si necesitas ayuda puedes llamar a mesa de ayuda al +56 225441122 entre 08:00 y 18:00 hrs. de lunes a viernes.
                  </DialogDescription>
                </DialogHeader>
              </DialogContent>
            </Dialog>
          </div>
        </div>
      </div>
    </header>
  )
}

