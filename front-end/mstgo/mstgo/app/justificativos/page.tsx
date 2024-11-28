'use client'

import { useState } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { CalendarIcon } from 'lucide-react'
import { format } from "date-fns"
import { cn } from "@/lib/utils"
import { Calendar } from "@/components/ui/calendar"
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"

// Datos de ejemplo para los trabajadores
const trabajadores = [
  { id: '1', nombre: 'Juan Pérez' },
  { id: '2', nombre: 'María González' },
  { id: '3', nombre: 'Carlos Rodríguez' },
]

export default function Justificativos() {
  const [trabajador, setTrabajador] = useState<string>('')
  const [date, setDate] = useState<Date>()
  //const [minutes, setMinutes] = useState<string>('') //Eliminado
  const [message, setMessage] = useState<string>('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (trabajador && date) {
      const trabajadorSeleccionado = trabajadores.find(t => t.id === trabajador)
      setMessage(`Justificativo ingresado con éxito para ${trabajadorSeleccionado?.nombre} el ${format(date, 'dd/MM/yyyy')}.`)
    } else {
      setMessage('Por favor, complete todos los campos.')
    }
  }

  return (
    <div className="flex flex-col h-screen">
      <TopBar />
      <div className="flex flex-1 pt-16">
        <Sidebar />
        <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
          <h1 className="text-2xl font-semibold text-gray-800 mb-4">Justificativos</h1>
          
          <Card className="mb-6">
            <CardHeader>
              <CardTitle>Instrucciones</CardTitle>
              <CardDescription>
                En esta sección podrá ingresar justificativos por ausencias o retrasos mayores a 70 miunutos..
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>Pasos para ingresar un justificativo:</p>
              <ol className="list-decimal list-inside mt-2">
                <li>Seleccione el trabajador para el cual desea ingresar el justificativo.</li>
                <li>Seleccione la fecha del justificativo.</li>
                <li>Haga clic en "Ingresar Justificativo" para registrar la información.</li>
              </ol>
            </CardContent>
          </Card>

          <Card>
            <CardHeader>
              <CardTitle>Ingresar Justificativo</CardTitle>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit} className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="trabajador">Trabajador</Label>
                  <Select onValueChange={setTrabajador}>
                    <SelectTrigger id="trabajador">
                      <SelectValue placeholder="Seleccione un trabajador" />
                    </SelectTrigger>
                    <SelectContent>
                      {trabajadores.map((t) => (
                        <SelectItem key={t.id} value={t.id}>{t.nombre}</SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
                <div className="flex flex-col sm:flex-row gap-4">
                  <div className="flex-1">
                    <Label htmlFor="date">Fecha</Label>
                    <Popover>
                      <PopoverTrigger asChild>
                        <Button
                          variant={"outline"}
                          className={cn(
                            "w-full justify-start text-left font-normal",
                            !date && "text-muted-foreground"
                          )}
                        >
                          <CalendarIcon className="mr-2 h-4 w-4" />
                          {date ? format(date, "PPP") : <span>Seleccione una fecha</span>}
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0">
                        <Calendar
                          mode="single"
                          selected={date}
                          onSelect={setDate}
                          initialFocus
                        />
                      </PopoverContent>
                    </Popover>
                  </div>
                  {/*Eliminado el bloque de minutos*/}
                </div>
                <Button type="submit">Ingresar Justificativo</Button>
              </form>
            </CardContent>
          </Card>

          {message && (
            <Alert className="mt-4">
              <AlertDescription>{message}</AlertDescription>
            </Alert>
          )}
        </main>
      </div>
    </div>
  )
}

