import { Button } from "@/components/ui/button"
import { Armchair } from 'lucide-react'
import Link from "next/link"

export function TopBar() {
  return (
    <header className="bg-black shadow-sm fixed top-0 left-0 right-0 z-10">
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
          <div>
            <Button asChild variant="default" className="bg-orange-500 hover:bg-orange-600 text-white">
              <Link href="/">
                Inicio
              </Link>
            </Button>
          </div>
        </div>
      </div>
    </header>
  )
}

