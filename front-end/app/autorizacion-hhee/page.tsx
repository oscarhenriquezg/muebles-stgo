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

export default function AutorizacionHHEE() {
  const [date, setDate] = useState<Date>()
  const [hours, setHours] = useState<string>('')
  const [message, setMessage] = useState<string>('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (date && hours) {
      setMessage(`Horas extras autorizadas con éxito para el ${format(date, 'dd/MM/yyyy')} por ${hours} horas.`)
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
          <h1 className="text-2xl font-semibold text-gray-800 mb-4">Autorización de Horas Extras</h1>
          
          <Card className="mb-6">
            <CardHeader>
              <CardTitle>Instrucciones</CardTitle>
              <CardDescription>
                En esta sección podrá autorizar horas extras para los empleados.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>Pasos para autorizar horas extras:</p>
              <ol className="list-decimal list-inside mt-2">
                <li>Seleccione la fecha para la autorización de horas extras.</li>
                <li>Ingrese la cantidad de horas extras autorizadas.</li>
                <li>Haga clic en "Autorizar Horas Extras" para registrar la información.</li>
              </ol>
            </CardContent>
          </Card>

          <Card>
            <CardHeader>
              <CardTitle>Autorizar Horas Extras</CardTitle>
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
                    <Label htmlFor="hours">Horas Extras Autorizadas</Label>
                    <Input
                      id="hours"
                      type="number"
                      step="0.5"
                      placeholder="Ingrese las horas extras"
                      value={hours}
                      onChange={(e) => setHours(e.target.value)}
                    />
                  </div>
                </div>
                <Button type="submit">Autorizar Horas Extras</Button>
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

