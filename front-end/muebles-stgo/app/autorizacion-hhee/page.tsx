'use client'

import { useState, useEffect, useCallback } from 'react'
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
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

interface Autorizacion {
  idAutorizacionesHhee: number;
  rutEmpleado: string;
  fecha: string;
  autorizado: boolean;
}

export default function AutorizacionHHEE() {
  const [date, setDate] = useState<Date | undefined>(undefined)
  const [rut, setRut] = useState<string>('')
  const [submitStatus, setSubmitStatus] = useState<string>('')
  const [attemptedSubmit, setAttemptedSubmit] = useState(false)
  const [autorizaciones, setAutorizaciones] = useState<Autorizacion[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const fetchAutorizaciones = useCallback(async () => {
    setIsLoading(true)
    setError(null)
    try {
      const response = await fetch('http://localhost:8080/api/autorizaciones')
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      const data = await response.json()
      setAutorizaciones(data)
    } catch (error) {
      console.error('Error:', error)
      setError(error instanceof Error ? error.message : 'An unexpected error occurred')
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchAutorizaciones()
  }, [fetchAutorizaciones])

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setAttemptedSubmit(true)
    if (!date || !rut) {
      setSubmitStatus('Error: Por favor, complete todos los campos.')
      return
    }
    setSubmitStatus('Enviando...')
    try {
      const response = await fetch('http://localhost:8080/api/autorizaciones', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          rutEmpleado: rut,
          fecha: date.toISOString().split('T')[0],
          autorizado: true,
        }),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      const data = await response.json()
      setSubmitStatus('Autorización de horas extras enviada con éxito')
      setRut('')
      setDate(undefined)
      setAttemptedSubmit(false) // Added to reset attemptedSubmit after successful submission
      fetchAutorizaciones()
    } catch (error) {
      console.error('Error:', error)
      setSubmitStatus(error instanceof Error ? error.message : 'An unexpected error occurred')
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
                <CardTitle>Nueva Autorización de Horas Extras</CardTitle>
                <CardDescription>Ingrese los detalles para autorizar horas extras del trabajador.</CardDescription>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleSubmit} className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="rut">RUT del Trabajador</Label>
                    <Input
                        id="rut"
                        placeholder="Ingrese el RUT del trabajador"
                        value={rut}
                        onChange={(e) => setRut(e.target.value)}
                        required
                    />
                  </div>

                  <div className="space-y-2">
                    <Label>Fecha</Label>
                    <Popover>
                      <PopoverTrigger asChild>
                        <Button
                            variant={"outline"}
                            className={cn(
                                "w-full justify-start text-left font-normal",
                                !date && "text-muted-foreground"
                            )}
                        >
                          <CalendarIcon className="mr-2 h-4 w-5" />
                          {date ? format(date, "PPP") : <span>Seleccione una fecha</span>}
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0">
                        <Calendar
                            mode="single"
                            selected={date}
                            onSelect={setDate}
                            initialFocus
                            required
                        />
                      </PopoverContent>
                    </Popover>
                    {!date && attemptedSubmit && ( // Updated error message display logic
                        <p className="text-sm text-red-500 mt-1">Por favor, seleccione una fecha</p>
                    )}
                  </div>

                  <Button type="submit">Autorizar Horas Extras</Button>
                </form>

                {submitStatus && (
                    <Alert className="mt-4">
                      <AlertDescription>{submitStatus}</AlertDescription>
                    </Alert>
                )}
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle>Autorizaciones de Horas Extras</CardTitle>
                <CardDescription>Lista de autorizaciones de horas extras registradas.</CardDescription>
              </CardHeader>
              <CardContent>
                {isLoading ? (
                    <p>Cargando autorizaciones...</p>
                ) : error ? (
                    <Alert variant="destructive">
                      <AlertDescription>{error}</AlertDescription>
                    </Alert>
                ) : (
                    <Table>
                      <TableHeader>
                        <TableRow>
                          <TableHead>ID</TableHead>
                          <TableHead>RUT Empleado</TableHead>
                          <TableHead>Fecha</TableHead>
                          <TableHead>Autorizado</TableHead>
                        </TableRow>
                      </TableHeader>
                      <TableBody>
                        {autorizaciones.map((autorizacion) => (
                            <TableRow key={autorizacion.idAutorizacionesHhee}>
                              <TableCell>{autorizacion.idAutorizacionesHhee}</TableCell>
                              <TableCell>{autorizacion.rutEmpleado}</TableCell>
                              <TableCell>{new Date(autorizacion.fecha).toLocaleDateString()}</TableCell>
                              <TableCell>{autorizacion.autorizado ? 'Sí' : 'No'}</TableCell>
                            </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                )}
              </CardContent>
            </Card>
          </main>
        </div>
      </div>
  )
}

