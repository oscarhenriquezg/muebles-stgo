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

export default function Justificativos() {
  const [date, setDate] = useState<Date>()
  const [minutes, setMinutes] = useState<string>('')
  const [message, setMessage] = useState<string>('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (date && minutes) {
      setMessage(`Justificativo ingresado con éxito para el ${format(date, 'dd/MM/yyyy')} por ${minutes} minutos.`)
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
                En esta sección podrá ingresar justificativos por ausencias o retrasos.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>Pasos para ingresar un justificativo:</p>
              <ol className="list-decimal list-inside mt-2">
                <li>Seleccione la fecha del justificativo.</li>
                <li>Ingrese la cantidad de minutos a justificar.</li>
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
                  <div className="flex-1">
                    <Label htmlFor="minutes">Minutos Justificados</Label>
                    <Input
                      id="minutes"
                      type="number"
                      placeholder="Ingrese los minutos"
                      value={minutes}
                      onChange={(e) => setMinutes(e.target.value)}
                    />
                  </div>
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

