'use client'

import { useState, useEffect } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { Progress } from "@/components/ui/progress"
import Link from 'next/link'
import { BarChart2 } from 'lucide-react'

export default function CalculoPlanillas() {
  const [isCalculating, setIsCalculating] = useState(false)
  const [calculationStatus, setCalculationStatus] = useState<string | null>(null)
  const [progress, setProgress] = useState(0)

  useEffect(() => {
    if (isCalculating) {
      const interval = setInterval(() => {
        setProgress((prevProgress) => {
          if (prevProgress >= 100) {
            clearInterval(interval)
            return 100
          }
          return prevProgress + 10
        })
      }, 500)

      return () => clearInterval(interval)
    }
  }, [isCalculating])

  const handleCalculate = async () => {
    setIsCalculating(true)
    setCalculationStatus('Calculando planillas...')
    setProgress(0)

    // Simulación de cálculo (reemplazar con llamada real a la API)
    setTimeout(() => {
      setIsCalculating(false)
      setCalculationStatus('Cálculo de planillas completado con éxito.')
      setProgress(100)
    }, 5000)
  }

  return (
    <div className="flex flex-col h-screen">
      <TopBar />
      <div className="flex flex-1 pt-16">
        <Sidebar />
        <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
          <h1 className="text-2xl font-semibold text-gray-800 mb-4">Cálculo de Planillas</h1>
          
          <Card>
            <CardHeader>
              <CardTitle>Procesar Planillas</CardTitle>
              <CardDescription>Inicie el proceso de cálculo de planillas para el mes actual.</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-4">
                <Button 
                  onClick={handleCalculate} 
                  disabled={isCalculating}
                  className="w-full sm:w-auto"
                >
                  {isCalculating ? 'Calculando...' : 'Calcular Planillas'}
                </Button>

                {isCalculating && (
                  <div className="space-y-2">
                    <Progress value={progress} className="w-full" />
                    <p className="text-sm text-gray-600 text-center">{progress}% Completado</p>
                  </div>
                )}

                {calculationStatus && !isCalculating && (
                  <Alert>
                    <AlertDescription>{calculationStatus}</AlertDescription>
                  </Alert>
                )}

                {calculationStatus === 'Cálculo de planillas completado con éxito.' && (
                  <Link 
                    href="/reportes-sueldos" 
                    className="inline-flex items-center justify-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-orange-500 hover:bg-orange-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-orange-500 transition-colors duration-200"
                  >
                    <BarChart2 className="mr-2 h-5 w-5" />
                    Ver Reporte de Sueldos
                  </Link>
                )}
              </div>
            </CardContent>
          </Card>
        </main>
      </div>
    </div>
  )
}

