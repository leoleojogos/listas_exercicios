package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.*;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
@RequestMapping("/fipe")
class FipeController {

	private final RestTemplate restTemplate = new RestTemplate();
	private static final String API_BASE_URL = "https://parallelum.com.br/fipe/api/v1";

	@GetMapping("/marcas")
	public ResponseEntity<Object> getMarcas() {
		try {
			String url = String.format("%s/carros/marcas", API_BASE_URL);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	@GetMapping("/marcas/{marcaId}/modelos")
	public ResponseEntity<Object> getModelos(@PathVariable String marcaId) {
		try {
			if (!isNumeric(marcaId)) {
				return ResponseEntity.badRequest().body("{\"error\":\"Brand ID must be numeric\"}");
			}
			String url = String.format("%s/carros/marcas/%s/modelos", API_BASE_URL, marcaId);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	@GetMapping("/marcas/{marcaId}/modelos/{modeloId}/anos")
	public ResponseEntity<Object> getAnos(@PathVariable String marcaId, @PathVariable String modeloId) {
		try {
			if (!isNumeric(marcaId) || !isNumeric(modeloId)) {
				return ResponseEntity.badRequest().body("{\"error\":\"Brand ID and Model ID must be numeric\"}");
			}
			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos", API_BASE_URL, marcaId, modeloId);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	@GetMapping("/marcas/{marcaId}/modelos/{modeloId}/anos/{anoId}")
	public ResponseEntity<Object> getValor(@PathVariable String marcaId, @PathVariable String modeloId, @PathVariable String anoId) {
		try {
			if (!isNumeric(marcaId) || !isNumeric(modeloId)) {
				return ResponseEntity.badRequest().body("{\"error\":\"Brand ID and Model ID must be numeric\"}");
			}
			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos/%s", API_BASE_URL, marcaId, modeloId, anoId);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	private boolean isNumeric(String str) {
		if (str == null || str.trim().isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@GetMapping("/valor")
	public ResponseEntity<CarResponse> getCarValueByParams(
			@RequestParam String marcaId, 
			@RequestParam String modeloId, 
			@RequestParam String anoId) {
		try {
			if (!isNumeric(marcaId) || !isNumeric(modeloId)) {
				return ResponseEntity.badRequest().build();
			}

			if (marcaId == null || modeloId == null || anoId == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos/%s", 
				API_BASE_URL, marcaId, modeloId, anoId);
			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> responseBody = response.getBody();
				CarResponse carResponse = new CarResponse();
				String valorStr = responseBody.get("Valor").toString();
				Double valor = parseValorToDouble(valorStr);
				carResponse.setValor(valor);
				carResponse.setMes(responseBody.get("MesReferencia").toString());
				return ResponseEntity.ok(carResponse);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/valor")
	public ResponseEntity<Object> getCarValue(@RequestBody CarRequest carRequest) {
		try {
			if (carRequest.getMarca() == null || carRequest.getModelo() == null || carRequest.getAno() == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"All fields are required: marca, modelo, ano\"}");
			}

			String brandId = findBrandIdByName(carRequest.getMarca());
			if (brandId == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"Brand not found: " + carRequest.getMarca() + "\"}");
			}

			String modelId = findModelIdByName(brandId, carRequest.getModelo());
			if (modelId == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"Model not found: " + carRequest.getModelo() + "\"}");
			}

			String yearId = findYearId(brandId, modelId, carRequest.getAno().toString());
			if (yearId == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"Year not found: " + carRequest.getAno() + "\"}");
			}

			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos/%s", 
				API_BASE_URL, brandId, modelId, yearId);
			ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				Map<String, Object> responseBody = response.getBody();
				
				String valorStr = responseBody.get("Valor").toString();
				Double valor = parseValorToDouble(valorStr);
				
				CarResponse carResponse = new CarResponse();
				carResponse.setValor(valor);
				carResponse.setMes(responseBody.get("MesReferencia").toString());
				return ResponseEntity.ok(carResponse);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"error\":\"Error processing request: " + e.getMessage() + "\"}");
		}
	}

	@SuppressWarnings("unchecked")
	private String findBrandIdByName(String brandName) {
		try {
			String url = String.format("%s/carros/marcas", API_BASE_URL);
			ResponseEntity<java.util.List> response = restTemplate.getForEntity(url, java.util.List.class);
			
			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				java.util.List<Map<String, Object>> brands = response.getBody();
				for (Map<String, Object> brand : brands) {
					if (brand.get("nome").toString().equalsIgnoreCase(brandName)) {
						return brand.get("codigo").toString();
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private String findModelIdByName(String brandId, String modelName) {
		try {
			String url = String.format("%s/carros/marcas/%s/modelos", API_BASE_URL, brandId);
			ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
			
			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				Map<String, Object> responseBody = response.getBody();
				java.util.List<Map<String, Object>> models = (java.util.List<Map<String, Object>>) responseBody.get("modelos");
				
				if (models != null) {
					for (Map<String, Object> model : models) {
						if (model.get("nome").toString().toLowerCase().contains(modelName.toLowerCase())) {
							return model.get("codigo").toString();
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private String findYearId(String brandId, String modelId, String year) {
		try {
			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos", API_BASE_URL, brandId, modelId);
			ResponseEntity<java.util.List> response = restTemplate.getForEntity(url, java.util.List.class);
			
			if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				java.util.List<Map<String, Object>> years = response.getBody();
				
				for (Map<String, Object> yearObj : years) {
					String yearName = yearObj.get("nome").toString();
					if (yearName.contains(year)) {
						return yearObj.get("codigo").toString();
					}
				}
				
				System.out.println("Available years for brand " + brandId + " model " + modelId + ":");
				for (Map<String, Object> yearObj : years) {
					System.out.println("- " + yearObj.get("nome") + " (codigo: " + yearObj.get("codigo") + ")");
				}
			}
		} catch (Exception e) {
			System.err.println("Error finding year ID: " + e.getMessage());
		}
		return null;
	}

	@GetMapping("/anos-disponiveis")
	public ResponseEntity<Object> getAvailableYears(@RequestParam String marca, @RequestParam String modelo) {
		try {
			String brandId = findBrandIdByName(marca);
			if (brandId == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"Brand not found: " + marca + "\"}");
			}

			String modelId = findModelIdByName(brandId, modelo);
			if (modelId == null) {
				return ResponseEntity.badRequest().body("{\"error\":\"Model not found: " + modelo + "\"}");
			}

			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos", API_BASE_URL, brandId, modelId);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"error\":\"Error getting available years: " + e.getMessage() + "\"}");
		}
	}

	@GetMapping("/debug/anos/{brandId}/{modelId}")
	public ResponseEntity<Object> debugYears(@PathVariable String brandId, @PathVariable String modelId) {
		try {
			String url = String.format("%s/carros/marcas/%s/modelos/%s/anos", API_BASE_URL, brandId, modelId);
			Object result = restTemplate.getForObject(url, Object.class);
			return ResponseEntity.ok(result);
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	private Double parseValorToDouble(String valorStr) {
		try {
			String cleanValue = valorStr.replace("R$ ", "").replace(".", "").replace(",", ".");
			return Double.parseDouble(cleanValue);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
}

class CarRequest {
	private String marca;
	private String modelo;
	private Integer ano;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
}

class CarResponse {
	private Double valor;
	private String mes;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
}
